<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use RealRashid\SweetAlert\Facades\Alert;

class MemberController extends Controller
{
    public function index()
    {
        $user = User::where('roles','member')->get();
        return view('admin.user.index',['user' => $user]);
    }

    public function destroy($id)
    {
        $user = User::where('id',$id)->first();
        $user->delete();
        Alert::success("Success", "kamu berhasil menghapus data");
        return redirect()->route('member.index');
    }
}
