<?php

namespace App\Http\Controllers;

use Carbon\Carbon;
use App\Models\Resep;
use Illuminate\Support\Str;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;

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

        $this->validate($request, ['gambar' => 'required|image|mimes:jpg,png,jpeg,gif,svg|max:2048']);

        $foto = $request->file('gambar');
        $destinationPath = 'images/';
        $profileImage = Str::slug($request->nama) .'-'.Carbon::now()->format('YmdHis')."." . $foto->getClientOriginalExtension();
        $foto->move($destinationPath, $profileImage);

        Resep::create([
            'judul' => $request->judul,
            'deskripsi' => $request->deskripsi,
            'referensi' => $request->referensi,
            // 'author' => $user,
            'gambar' => $profileImage,
        ]);

        return redirect()->route('resep.index');
    }

    /**
     * Display the specified resource.
     */
    public function show(Resep $resep)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit($id)
    {
        $resep = Resep::where('id',$id)->first();
        return view('admin.resep-makanan.edit',['resep' => $resep]);
        // dd($resep);
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(Request $request, Resep $resep)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Resep $resep)
    {
        //
    }
}
