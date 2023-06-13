<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Bahan extends Model
{
    use HasFactory;

    protected $guarded = ['id'];

    public function resep()
    {
        return $this->belongsTo(Resep::class, 'resep_id');
    }
}
