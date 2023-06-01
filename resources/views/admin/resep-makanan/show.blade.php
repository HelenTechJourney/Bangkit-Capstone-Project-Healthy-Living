@extends('admin.base')

@section('content')
@section('title', 'Resep Makanan | Show')
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Show /</span> Resep Makanan</h4>
    <div class="row">
        <div class="col-md-12">
            <div class="card mb-4">
                <h5 class="card-header">Resep Makanan</h5>
                <div class="card-body mt-3">
                    <div class="mb-3">
                        <h5 class="text-uppercase">Gambar</h5>
                        <img src="{{asset('images/'.$resep->gambar)}}" width="30%" alt="">
                    </div>
                    <div class="mb-3">
                        <h5 class="text-uppercase">Judul</h5>
                        <p>{{$resep->judul}}</p>
                    </div>
                    <div class="mb-3">
                        <h5 class="text-uppercase">Author</h5>
                        <p>{{$resep->author}}</p>
                    </div>
                    <div class="mb-3">
                        <h5 class="text-uppercase">Deskripsi</h5>
                        <p>{!!$resep->deskripsi!!}</p>
                    </div>
                    <div class="mb-3">
                        <h5 class="text-uppercase">Referensi</h5>
                        <p>{{$resep->referensi}}</p>
                    </div>
                    <div class="d-flex justify-content-end">
                        <a href="{{route('resep.index')}}" class="btn btn-danger">Kembali</a>
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
