<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class ArtikelRequest extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     */
    public function authorize(): bool
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array<string, \Illuminate\Contracts\Validation\ValidationRule|array|string>
     */
    public function rules(): array
    {
        return [
            'judul' => "required",
            'deskripsi' => "required",
            'referensi' => "required",
            'gambar' => 'required|image|mimes:jpg,png,jpeg,gif,svg|max:2048'
        ];
    }
    public function messages()
    {
        return [
            "judul.required" => "Judul Tidak Boleh Kosong",
            "deskripsi.required" => "Deskripsi Tidak Boleh Kosong",
            "referensi.required" => "Referensi Tidak Boleh Kosong",
            "gambar.required" => "Gambar Tidak Boleh Kosong",
            'gambar.image' => 'file harus gambar',
            'gambar.mimes' => 'file dalam bentuk jpg,png,jpeg,gif dan svg',
            'gambar.max' => 'gambar tidak boleh lebih dari 2mb'
        ];
    }
}
