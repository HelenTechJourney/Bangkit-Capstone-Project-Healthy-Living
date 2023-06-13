<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Resep extends Model
{
    use HasFactory;

    protected $guarded = ['id'];

    protected $with = ['bahans','caraMembuats'];

    public function bahans()
    {
        return $this->hasMany(Bahan::class, 'resep_id');
    }

    public function caraMembuats()
    {
        return $this->hasMany(CaraMembuat::class, 'resep_id');
    }
}
