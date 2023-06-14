<?php

use App\Http\Controllers\ArtikelController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\ResepController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/



Route::post('login',[AuthController::class,'login_app']);
Route::post('daftar',[AuthController::class,'daftar']);


Route::middleware('auth:sanctum')->group(function () {
    Route::post('user_data_form1',[AuthController::class,'user_data_form1']);
    Route::get('user_data_form2',[AuthController::class,'user_data_form2']);
    Route::post('update_profil/{id}',[AuthController::class,'update_profil']);

    Route::get('me',[AuthController::class,'me']);

    Route::get('logout',[AuthController::class,'logout_app']);
    // artikel
    Route::get('artikel',[ArtikelController::class,'artikel']);
    Route::get('detail_artikel/{id}',[ArtikelController::class,'detail_artikel']);
    // resep makanan
    Route::get('resep',[ResepController::class,'resep']);
    Route::get('detail_resep/{id}',[ResepController::class,'detail_resep']);
});
