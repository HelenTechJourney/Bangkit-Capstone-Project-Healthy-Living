<?php

namespace App\Http\Controllers;

use App\Models\CaraMembuat;
use Illuminate\Http\Request;
use RealRashid\SweetAlert\Facades\Alert;

class CaraMembuatController extends Controller
{
    public function store(Request $request){
        CaraMembuat::create([
            'resep_id' => $request->resep_id,
            'deskripsi' => $request->deskripsi,
        ]);
        Alert::success("Success", "kamu berhasil menambahkan data");
        return redirect()->back();
    }
    public function destroy($id){
      $cara_membuat = CaraMembuat::where('id',$id)->first();
      $cara_membuat->delete();
      Alert::success("Success", "kamu berhasil menghapus data");
      return redirect()->back();
    }
}
