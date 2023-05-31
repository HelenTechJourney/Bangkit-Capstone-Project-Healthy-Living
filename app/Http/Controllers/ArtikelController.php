<?php

namespace App\Http\Controllers;

use App\Http\Requests\ArtikelRequest;
use App\Http\Requests\ArtikelRequestUpdate;
use App\Models\artikel;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Str;
use Illuminate\Support\Facades\Auth;
use RealRashid\SweetAlert\Facades\Alert;

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
        $profileImage = Str::slug($request->nama) . '-' . Carbon::now()->format('YmdHis') . "." . $foto->getClientOriginalExtension();
        $foto->move($destinationPath, $profileImage);

        artikel::create([
            'judul' => $request->judul,
            'deskripsi' => $request->deskripsi,
            'referensi' => $request->referensi,
            // 'author' => $user,
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
            $profileImage = Str::slug($request->nama) . '-' . Carbon::now()->format('YmdHis') . "." . $foto->getClientOriginalExtension();
            $foto->move($destinationPath, $profileImage);

            $file_path = public_path() . "/images/" . $artikel->foto;
            unlink($file_path);
            $artikel->update([
               'judul' => $request->judul,
               'deskripsi' => $request->deskripsi,
               'referensi' => $request->referensi,
            //    'author' => $user,
               'gambar' => $profileImage
            ]);
        } else {
            $artikel->update([
                'judul' => $request->judul,
               'deskripsi' => $request->deskripsi,
               'referensi' => $request->referensi,
            //    'author' => $user,
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
        $artikel->delete();
        Alert::success("Success", "kamu berhasil menghapus data");
        return redirect()->route('artikel.index');
    }
}
