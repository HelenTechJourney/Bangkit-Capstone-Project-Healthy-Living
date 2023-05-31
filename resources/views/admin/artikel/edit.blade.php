@extends('admin.base')

@section('content')
@section('title', 'Artikel | Edit')
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Forms /</span> Artikel</h4>
    <div class="row">
        <div class="col-md-12">
            <div class="card mb-4">
                <h5 class="card-header">Artikel</h5>
                <div class="card-body">
                    <form action="{{ route('artikel.update', $artikel->id) }}" method="post"
                        enctype="multipart/form-data">
                        @csrf
                        @method('put')
                        <div class="mb-3">
                            <label for="defaultFormControlInput" class="form-label">Gambar</label>
                            <input type="file" class="form-control @error('gambar') is-invalid @enderror"
                                id="defaultFormControlInput" name="gambar" value="{{ $artikel->gambar }}">
                            @error('gambar')
                                <div class="invalid-feedback">
                                    <i class="bi bi-exclamation-circle-fill"></i>
                                    {{ $message }}
                                </div>
                            @enderror
                        </div>
                        <div class="mb-3">
                            <label for="defaultFormControlInput" class="form-label">Judul</label>
                            <input type="text" class="form-control @error('judul') is-invalid @enderror"
                                id="defaultFormControlInput" name="judul" placeholder="Masukan Judul"
                                value="{{ $artikel->judul }}">
                            @error('judul')
                                <div class="invalid-feedback">
                                    <i class="bi bi-exclamation-circle-fill"></i>
                                    {{ $message }}
                                </div>
                            @enderror
                        </div>
                        <div class="mb-3">
                            <label for="defaultFormControlInput" class="form-label">Referensi</label>
                            <input type="text" class="form-control @error('referensi') is-invalid @enderror"
                                id="defaultFormControlInput" name="referensi" placeholder="Masukan Referensi"
                                value="{{ $artikel->referensi }}">
                            @error('referensi')
                                <div class="invalid-feedback">
                                    <i class="bi bi-exclamation-circle-fill"></i>
                                    {{ $message }}
                                </div>
                            @enderror
                        </div>
                        <div class="mb-3">
                            <label class="form-label" for="basic-default-company">Deskripsi</label>
                            <textarea id="deskripsi" type="text" name="deskripsi" cols="30" rows="5"
                                class="form-control @error('deskripsi') is-invalid @enderror" style="height: 300px;">{{ $artikel->deskripsi }}</textarea>
                            @error('deskripsi')
                                <div class="invalid-feedback">
                                    <i class="bi bi-exclamation-circle-fill"></i>
                                    {{ $message }}
                                </div>
                            @enderror
                        </div>
                        <div class="d-flex justify-content-end">
                            <a href="{{ route('artikel.index') }}" class="btn btn-danger me-3">Kembali</a>
                            <button type="submit" class="btn btn-primary">Simpan</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection

@section('scripts')
<script>
    ClassicEditor
        .create(document.querySelector('#deskripsi'))
        .catch(error => {
            console.error(error);
        });
</script>
@endsection
