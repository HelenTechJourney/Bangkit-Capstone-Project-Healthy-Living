from flask import Flask, request, jsonify
from main import model

app = Flask(__name__)

@app.route('/solve', methods=['POST'])
def solve():
    day = request.form['day']
    kg = float(request.form['kg'])
    calories = float(request.form['calories'])

    sol = model(day, kg, calories)

    response = {
        'Food': sol['Food'].tolist(),
        'Quantity (g)': sol['Quantity (g)'].tolist(),
        'Cluster': sol['Cluster'].tolist()
    }

    return jsonify(response)

if __name__ == '__main__':
       app.run(host='0.0.0.0', port=5000, debug=False)
