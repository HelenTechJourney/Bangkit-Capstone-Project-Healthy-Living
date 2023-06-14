<?php

namespace App\Http\Controllers;

use Carbon\Carbon;
use App\Models\artikel;
use Illuminate\Support\Str;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\Http\Requests\ArtikelRequest;
use RealRashid\SweetAlert\Facades\Alert;
use App\Http\Requests\ArtikelRequestUpdate;

class ArtikelController extends Controller
{
    public function index()
    {
        $artikel = artikel::get();
        return view('admin.artikel.index', ['data' => $artikel]);
    }

    public function create()
    {
        return view('admin.artikel.create');
    }

    public function store(ArtikelRequest $request)
    {
        $user = Auth::user();

        $foto = $request->file('gambar');
        $destinationPath = 'images/';
        $baseURL = url('/');
        $profileImage = $baseURL. "/images/".Str::slug($request->judul) . '-' . Carbon::now()->format('YmdHis') . "." . $foto->getClientOriginalExtension();
        $foto->move($destinationPath, $profileImage);

        artikel::create([
            'judul' => $request->judul,
            'deskripsi' => $request->deskripsi,
            'referensi' => $request->referensi,
            'author' => $user->nama,
            'gambar' => $profileImage,
        ]);
        Alert::success("Success", "kamu berhasil menambahkan data");
        return redirect()->route('artikel.index');
    }

    public function show($id)
    {
        $artikel = artikel::where('id',$id)->first();
        return view('admin.artikel.show',['artikel' => $artikel]);
    }

    public function edit($id)
    {
        $artikel = artikel::where('id', $id)->first();
        return view('admin.artikel.edit', ['artikel' => $artikel]);

    }

    public function update(ArtikelRequestUpdate $request, $id)
    {
        $user = Auth::user();
        $artikel = artikel::where('id', $id)->first();

        if ($request->gambar) {
            $foto = $request->file('gambar');
            $destinationPath = 'images/';
            $baseURL = url('/');
            $profileImage = $baseURL. "/images/" . Str::slug($request->judul) . '-' . Carbon::now()->format('YmdHis') . "." . $foto->getClientOriginalExtension();
            $foto->move($destinationPath, $profileImage);
            if ($artikel->gambar) {
                $file_path = Str::replace($baseURL . '/images/', '', public_path() . '/images/' . $artikel->gambar);
                unlink($file_path);
            }
            $artikel->update([
               'judul' => $request->judul,
               'deskripsi' => $request->deskripsi,
               'referensi' => $request->referensi,
               'author' => $user->nama,
               'gambar' => $profileImage
            ]);
        } else {
            $artikel->update([
                'judul' => $request->judul,
               'deskripsi' => $request->deskripsi,
               'referensi' => $request->referensi,
               'author' => $user->nama,
            ]);
        }
        Alert::success("Success", "kamu berhasil memperbarui data");

        return redirect()->route('artikel.index');
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy($id)
    {
        $artikel = artikel::where('id', $id)->first();
        if ($artikel->gambar) {
            $baseURL = url('/');
            $file_path = Str::replace($baseURL . '/images/', '', public_path() . '/images/' . $artikel->gambar);
            unlink($file_path);
        }
        $artikel->delete();
        Alert::success("Success", "kamu berhasil menghapus data");
        return redirect()->route('artikel.index');
    }

    ##api##
    public function artikel()
    {
        $artikel = artikel::get();
        return response()->json([
            "message" => "kamu berhasil melihat seluruh data artikel",
            "data" => $artikel
        ]);
    }

    ##end api##
}
