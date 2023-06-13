<?php

namespace App\Http\Controllers;

use App\Models\Bahan;
use Illuminate\Http\Request;
use RealRashid\SweetAlert\Facades\Alert;

class BahanController extends Controller
{
    public function store(Request $request){
        Bahan::create([
            'resep_id' => $request->resep_id,
            'deskripsi' => $request->deskripsi,
        ]);
        Alert::success("Success", "kamu berhasil menambahkan data");
        return redirect()->back();
    }
    public function destroy($id){
        $bahan = Bahan::where('id',$id)->first();
        $bahan->delete();
        Alert::success("Success", "kamu berhasil menghapus data");
        return redirect()->back();
      }
}
