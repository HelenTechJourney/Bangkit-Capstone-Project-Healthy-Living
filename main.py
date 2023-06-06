import numpy as np
import pandas as pd 
from pulp import * 
import json

path = 'processed_dataset.csv'
dfd = pd.read_csv(path)

def build_nutritional_values(kg, calories):
    protein_calories = kg * 4
    carb_calories = calories / 2.
    fat_calories = calories - carb_calories - protein_calories
    res = {
        'carbohydrate calories': carb_calories,
        'total_fat calories': fat_calories,
        'protein calories': protein_calories
    }
    return res

def extract_gram(table):
    protein_grams = table['protein calories'] / 4.
    carbs_grams = table['carbohydrate calories'] / 4.
    fat_grams = table['total_fat calories'] / 9.
    res = {
        'Protein Grams': protein_grams,
        'Carbohydrates Grams': carbs_grams,
        'Fat Grams': fat_grams
    }
    return res

def model(day,kg,calories):
    G = extract_gram(build_nutritional_values(kg,calories))
    E = G['Carbohydrates Grams']
    F = G['Fat Grams']
    P = G['Protein Grams']
    day_data = dfd.loc[dfd['day'] == day].reset_index(drop=True)
    food = day_data.name.tolist()
    c  = day_data.calories.tolist()
    x  = pulp.LpVariable.dicts( "x", indices = food, lowBound=0, upBound=1.5, cat='Continuous', indexStart=[] )
    e = day_data.carbohydrate.tolist()
    f = day_data.total_fat.tolist()
    p = day_data.protein.tolist()
    prob  = pulp.LpProblem( "Diet", LpMinimize )
    prob += pulp.lpSum( [x[food[i]]*c[i] for i in range(len(food))]  )
    prob += pulp.lpSum( [x[food[i]]*e[i] for i in range(len(x)) ] )>=E
    prob += pulp.lpSum( [x[food[i]]*f[i] for i in range(len(x)) ] )>=F
    prob += pulp.lpSum( [x[food[i]]*p[i] for i in range(len(x)) ] )>=P
    prob.solve(PULP_CBC_CMD(msg=0))
    values = [v.varValue for v in prob.variables()]
    values = np.round(values, 2).astype(float)
    sol = pd.DataFrame({'Food': food, 'Quantity': values})
    sol = sol[sol['Quantity'] != 0.0]
    sol['Quantity (g)'] = (sol['Quantity'] * 100).round(2)
    sol.rename(columns={'Quantity': 'Quantity (g)'}, inplace=True)
    sol['Cluster'] = day_data.loc[sol.index, 'cluster'].tolist()
    
    output_dict = sol.to_dict(orient='list')
    print(json.dumps(output_dict))

    return sol

def main():
    day = input("Enter the day: ")
    kg = float(input("Enter the weight in kg: "))
    calories = float(input("Enter the target calories: "))
    model(day, kg, calories)

if __name__=="__main__":
    main()