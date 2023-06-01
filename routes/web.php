<?php

use App\Http\Controllers\ArtikelController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\MemberController;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ResepController;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/



Route::get('/',[AuthController::class,'index'])->name('login');
Route::post('/',[AuthController::class,'login'])->name('login_action');

Route::prefix('user')->middleware('auth')->group(function(){
    Route::get('logout',[AuthController::class,'logout'])->name('logout');
    Route::get('dashboard', function () {
        return view('admin.dashboard.index');
    })->name('dashboard');
    Route::resource('resep',ResepController::class);
    Route::resource('artikel',ArtikelController::class);
    Route::get('member',[MemberController::class,'index'])->name('member.index');
    Route::delete('member/{id}',[MemberController::class,'destroy'])->name('member.destroy');
});
