<?php

namespace App\Http\Controllers;

use Carbon\Carbon;
use App\Models\Resep;
use Illuminate\Support\Str;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use RealRashid\SweetAlert\Facades\Alert;

class ResepController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $resep_makanan = Resep::get();
        return view('admin.resep-makanan.index', ['data' => $resep_makanan]);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        return view('admin.resep-makanan.create');
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(Request $request)
    {
        $user = Auth::user();

        $foto = $request->file('gambar');
        $destinationPath = 'images/';
        $profileImage = Str::slug($request->judul) . '-' . Carbon::now()->format('YmdHis') . "." . $foto->getClientOriginalExtension();
        $foto->move($destinationPath, $profileImage);

        Resep::create([
            'judul' => $request->judul,
            'deskripsi' => $request->deskripsi,
            'referensi' => $request->referensi,
            'author' => $user->nama,
            'gambar' => $profileImage,
        ]);
        Alert::success("Success", "kamu berhasil menambahkan data");
        return redirect()->route('resep.index');
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $resep = Resep::where('id', $id)->first();
        return view('admin.resep-makanan.show', ['resep' => $resep]);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit($id)
    {
        $resep = Resep::where('id', $id)->first();
        return view('admin.resep-makanan.edit', ['resep' => $resep]);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, $id)
    {
        $user = Auth::user();
        $resep = Resep::where('id', $id)->first();

        if ($request->gambar) {
            $foto = $request->file('gambar');
            $destinationPath = 'images/';
            $profileImage = Str::slug($request->judul) . '-' . Carbon::now()->format('YmdHis') . "." . $foto->getClientOriginalExtension();
            $foto->move($destinationPath, $profileImage);

            if ($resep->gambar) {
                $file_path = public_path() . "/images/" . $resep->gambar;
                unlink($file_path);
            }

            $resep->update([
                'judul' => $request->judul,
                'deskripsi' => $request->deskripsi,
                'referensi' => $request->referensi,
                'author' => $user->nama,
                'gambar' => $profileImage,
            ]);
        } else {
            $resep->update([
                'judul' => $request->judul,
                'deskripsi' => $request->deskripsi,
                'referensi' => $request->referensi,
                'author' => $user->nama,
            ]);
        }
        Alert::success("Success", "kamu berhasil memperbarui data");
        return redirect()->route('resep.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $resep = Resep::where('id', $id)->first();
        if ($resep->gambar) {
            $file_path = public_path() . "/images/" . $resep->gambar;
            unlink($file_path);
        }
        $resep->delete();

        Alert::success("Success", "kamu berhasil menghapus data");
        return redirect()->route('resep.index');
    }

    public function resep()
    {
        $resep = Resep::get();
        return response()->json([
            "message" => "kamu berhasil melihat seluruh data resep",
            'data' => $resep
        ]);
    }

    public function detail_resep($id)
    {
        $resep = Resep::where('id',$id)->first();
        return response()->json([
            "message" => "kamu berhasil melihat detail data resep",
            'data' => $resep
        ]);
    }
}
