<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('users', function (Blueprint $table) {
            $table->id();
            $table->string('nama');
            $table->enum('jenis_kelamin', ['Laki - laki', 'Perempuan'])->nullable();
            $table->integer('usia')->nullable();
            $table->double('tinggi_badan')->nullable();
            $table->double('berat_badan')->nullable();
            $table->enum('aktivitas_fisik', ['Tidak aktif', 'Sedikit aktif(aktivitas fisik ringan 1-3 kali seminggu)', 'Aktif(aktivitas fisik ringan 3-5 kali seminggu)', 'Sangat aktif(aktivitas fisik berat 6-7 kali seminggu)', 'Sangat aktif sekali(aktivitas fisik berat setiap hari atau aktivitas fisik berat 2 kali sehari)'])->nullable();
            $table->string('email');
            $table->string('password');
            $table->string('roles')->default('member');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('users');
    }
};
