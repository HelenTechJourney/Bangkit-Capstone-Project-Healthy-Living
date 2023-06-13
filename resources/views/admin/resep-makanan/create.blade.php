@extends('admin.base')

@section('content')
@section('title', 'Resep Makanan | Create')
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Forms /</span> Resep Makanan</h4>
    <div class="row">
        <div class="col-md-12">
            <div class="card mb-4">
                <h5 class="card-header">Resep Makanan</h5>
                <div class="card-body">
                  <form action="{{route('resep.store')}}" method="post" enctype="multipart/form-data">
                    @csrf
                    <div class="mb-3">
                        <label for="defaultFormControlInput" class="form-label">Gambar</label>
                        <input type="file" class="form-control" id="defaultFormControlInput" name="gambar"
                            value="{{old('gambar')}}">
                    </div>
                    <div class="mb-3">
                        <label for="defaultFormControlInput" class="form-label">Judul</label>
                        <input type="text" class="form-control" id="defaultFormControlInput" name="judul" placeholder="Masukan Judul"
                            value="{{old('judul')}}">
                    </div>
                    <div class="mb-3">
                        <label for="defaultFormControlInput" class="form-label">Referensi</label>
                        <input type="text" class="form-control" id="defaultFormControlInput" name="referensi" placeholder="Masukan Referensi"
                            value="{{old('referensi')}}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="basic-default-company">Deskripsi</label>
                        <textarea id="deskripsi" type="text" name="deskripsi" cols="30" rows="5"
                                  class="form-control" style="height: 300px;">{{old('deskripsi')}}</textarea>
                    </div>
                    <div class="d-flex justify-content-end">
                        <a href="{{route('resep.index')}}" class="btn btn-danger me-3">Kembali</a>
                        <button type="submit" class="btn btn-primary">Simpan</button>
                    </div>
                  </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
