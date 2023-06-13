@extends('admin.base')

@section('content')
@section('title', 'Artikel | Show')
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Show /</span> Artikel</h4>
    <div class="row">
        <div class="col-md-12">
            <div class="card mb-4">
                <h5 class="card-header">Artikel</h5>
                <div class="card-body mt-3">
                    <div class="row">
                        <div class="col-6">
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Gambar</label>
                                <a href="{{ $artikel->gambar }}" class="btn btn-primary w-100" target="_BLANK">Gambar</a>
                            </div>
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Judul</label>
                                <input class="form-control bg-light" value="{{ $artikel->judul }}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Author</label>
                                <input class="form-control bg-light" value="{{ $artikel->author }}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Referensi</label>
                                <input class="form-control bg-light" value="{{ $artikel->referensi }}" readonly>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Deskripsi</label>
                                <textarea class="form-control bg-light" cols="30" rows="12" readonly>{{ $artikel->deskripsi }}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <a href="{{route('artikel.index')}}" class="btn btn-danger">Kembali</a>
                    </div>
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
