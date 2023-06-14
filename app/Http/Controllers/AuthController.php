<?php

namespace App\Http\Controllers;

use Carbon\Carbon;
use App\Models\User;
use Illuminate\Support\Str;
use Illuminate\Http\Request;
use App\Http\Requests\UserRequest;
use App\Http\Requests\LoginRequest;
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
    public function login_app(LoginRequest $request)
    {
        $user = User::where('email', $request->email)->first();

        if (!$user || !Hash::check($request->password, $user->password)) {
            throw ValidationException::withMessages([
                'email' => ['Kredensial yang diberikan salah'],
            ]);
        }
        $token = $user->createToken($user->nama)->plainTextToken;
        return response()->json([
            "error" => 'false',
            'message' => 'kamu berhasil login',
            "resultLogin" => [
                'nama' => $user->nama,
                'token' => $token
            ]
        ]);
    }

    public function logout_app(Request $request)
    {
        $request->user()->currentAccessToken()->delete();

        return response()->json([
            "message" => "Token Berhasil Dihapus"
        ]);
    }

    public function me()
    {
        $user = Auth::user();
        $user->makeHidden(['created_at', 'updated_at']);
        return response()->json([
            "message" => "data pengguna yang sedang login",
            "data" => $user
        ]);
    }

    public function daftar(UserRequest $request)
    {
        User::create([
            'nama' => $request->nama,
            'email' => $request->email,
            'password' => $request->password,
        ]);

        $userLogin = User::where('email', $request->email)->first();
        if (!$userLogin || !Hash::check($request->password, $userLogin->password)) {
            throw ValidationException::withMessages([
                'email' => ['Kredensial yang diberikan salah'],
            ]);
        }

        $userLogin->createToken($userLogin->nama)->plainTextToken;

        return response()->json([
            "error" => "false",
            "message" => "kamu berhasil membuat data user",
            'resultDaftar' => [
                'nama' => $userLogin->nama,
                " token" => $userLogin->createToken($userLogin->nama)->plainTextToken
            ]
        ], 200);
    }

    public function update_profil(Request $request, $id)
    {
        $this->validate($request, ['gambar' => 'required|image|mimes:jpg,png,jpeg,gif,svg|max:2048',]);

        $user = User::where('id', $id)->first();
        if ($request->gambar) {
            $foto = $request->file('gambar');
            $destinationPath = 'images/';
            $baseURL = url('/');
            $profileImage = $baseURL . "/images/" . Str::slug($user->nama) . '-' . Carbon::now()->format('YmdHis') . "." . $foto->getClientOriginalExtension();
            $foto->move($destinationPath, $profileImage);
            if ($user->gambar) {
                $file_path = Str::replace($baseURL . '/images/', '', public_path() . '/images/' . $user->gambar);
                unlink($file_path);
            }
            $user->update([
                'nama' => $request->nama,
                'gambar' => $profileImage
            ]);
        } else {
            $user->update([
                'nama' => $user->nama,
            ]);
        }

        return response()->json([
            'message' => 'kamu berhasil mengupdate data user',
            'data' => $user
        ]);
    }

    public function user_data_form1(Request $request)
    {
        $userLogin = Auth::user();
        $user = User::where('id', $userLogin->id)->first();

        // tinggi badan dalam meter
        $bmr_pria = 66 + (13.7 * $user->berat_badan) + (5 * $user->tinggi_badan) - (6.8 * $user->usia);
        $bmr_wanita = 655 + (9.6 * $user->berat_badan) + (1.8 * $user->tinggi_badan) - (4.7 * $user->usia);
        $bmi = $user->berat_badan / (($request->tinggi_badan / 100) * ($request->tinggi_badan / 100));

        if ($request->jenis_kelamin == 'Laki - laki') {
            $user->update([
                'jenis_kelamin' => $request->jenis_kelamin,
                'usia' => $request->usia,
                'tinggi_badan' => $request->tinggi_badan,
                'berat_badan' => $request->berat_badan,
                'aktivitas_fisik' => $request->aktivitas_fisik,
                'bmi' => $bmi,
                'bmr' => $bmr_pria
            ]);
        } else
        if ($request->jenis_kelamin == 'Perempuan') {
            $user->update([
                'jenis_kelamin' => $request->jenis_kelamin,
                'usia' => $request->usia,
                'tinggi_badan' => $request->tinggi_badan,
                'berat_badan' => $request->berat_badan,
                'aktivitas_fisik' => $request->aktivitas_fisik,
                'bmi' => $bmi,
                'bmr' => $bmr_wanita
            ]);
        }

        return response()->json([
            'message' => 'kamu berhasil mengisi form 1 data user',
            'data' => [
                "jenis_kelamin" => $user->jenis_kelamin,
                "usia" => $user->usia,
                "tinggi_badan" => $user->tinggi_badan,
                "berat_badan" => $user->berat_badan,
                "aktivitas_fisik" => $user->aktivitas_fisik,
            ]
        ]);
    }
    public function user_data_form2()
    {
        $userLogin = Auth::user();
        $user = User::where('id', $userLogin->id)->first();
        if ($user->jenis_kelamin == 'Laki - laki') {
            $rekomendasiBeratBadan = ($user->tinggi_badan - 100) - (($user->tinggi_badan - 100) * 10 / 100);
        } else
        if ($user->jenis_kelamin == 'Perempuan') {
            $rekomendasiBeratBadan = ($user->tinggi_badan - 100) - (($user->tinggi_badan - 100) * 10 / 100);
        }

        return response()->json([
            'message' => 'kamu berhasil mengambil form 2 data user',
            'data' => [
                "bmr" => number_format($user->bmr, 3),
                "bmi" => number_format($user->bmi, 3),
                "rekomendasi_berat_badan" => $rekomendasiBeratBadan
            ]
        ]);
    }
    ##end api##
}
