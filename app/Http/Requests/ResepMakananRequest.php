<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class ResepMakananRequest extends FormRequest
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
            'judul' => 'required',
            'referensi' => 'required',
            'deskripsi' => 'required',
            'gambar' => 'required|image|mimes:jpg,png,jpeg,gif,svg|max:2048',
        ];
    }

    public function messages()
    {
        return [
            'judul.required' => 'judul tidak boleh kosong',
            'referensi.required' => 'referensi tidak boleh kosong',
            'deskripsi.required' => 'deskripsi tidak boleh kosong',
            'gambar.required' => 'gambar tidak boleh kosong',
            'gambar.image' => 'file harus gambar',
            'gambar.mimes' => 'file dalam bentuk jpg,png,jpeg,gif dan svg',
            'gambar.max' => 'gambar tidak boleh lebih dari 2mb',
        ];
    }
}
