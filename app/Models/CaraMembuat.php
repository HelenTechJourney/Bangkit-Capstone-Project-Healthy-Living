<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class CaraMembuat extends Model
{
    use HasFactory;
    protected $guarded = ['id'];

    public function resep(){
        $this->hasMany(Resep::class);
    }
}
