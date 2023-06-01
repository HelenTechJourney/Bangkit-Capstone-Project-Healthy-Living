<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
use Illuminate\Validation\ValidationException;

class AuthController extends Controller
{
    public function index()
    {
        return view('admin.login.index');
    }

    ##web##
    public function login(Request $request)
    {
        $krendensil = $request->validate([
            'email' => 'required',
            'password' => 'required'
        ]);
        $user = User::where('email', $request->email)->first();
        $user->createToken($user->email)->plainTextToken;
        Auth::attempt($krendensil);
        Auth::user();
        return redirect()->route('dashboard');
    }

    public function logout(Request $request)
    {
        $request->session()->flush();
        Auth::user()->tokens()->delete();
        Auth::logout();
        return redirect()->route('login');
    }
    ##end web##

    ##api##
    public function login_app(Request $request)
    {
        $request->validate([
            'email' => 'required',
            'password' => 'required'
        ]);

        $user = User::where('email', $request->email)->first();

        if (!$user || !Hash::check($request->password, $user->password)) {
            throw ValidationException::withMessages([
                'email' => ['Kredensial yang diberikan salah'],
            ]);
        }

        return $user->createToken($user->nama)->plainTextToken;
    }

    public function logout_app(Request $request)
    {
        $request->user()->currentAccessToken()->delete();

        return response()->json([
            "message" => "Token Berhasil Dihapus"
        ]);
    }

    public function daftar(Request $request)
    {
        User::create([
            'nama' => $request->nama,
            'username' => $request->username,
            'password' => $request->password,
            'jenis_kelamin' => $request->jenis_kelamin,
        ]);

        return response()->json([
            "message" => "kamu berhasil membuat data user",
        ]);
    }
    ##end api##
}
