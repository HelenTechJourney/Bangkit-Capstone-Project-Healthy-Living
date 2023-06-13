@extends('admin.base')

@section('content')
@section('title', 'Resep Makanan | Show')
@push('style')
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-bs5/datatables.bootstrap5.css') }}" />
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-responsive-bs5/responsive.bootstrap5.css') }}" />
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-buttons-bs5/buttons.bootstrap5.css') }}" />
@endpush
{{-- sweetalert --}}
@include('sweetalert::alert')
{{-- end sweetalert --}}
<div class="container-xxl flex-grow-1 container-p-y">
    <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Show /</span> Resep Makanan</h4>
    <div class="row">
        <div class="col-md-12">
            <div class="card mb-4">
                <h5 class="card-header">Resep Makanan</h5>
                <div class="card-body mt-3">
                    <div class="row">
                        <div class="col-6">
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Gambar</label>
                                <a href="{{ $resep->gambar }}" class="btn btn-primary w-100" target="_BLANK">Gambar</a>
                            </div>
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Judul</label>
                                <input class="form-control bg-light" value="{{ $resep->judul }}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Author</label>
                                <input class="form-control bg-light" value="{{ $resep->author }}" readonly>
                            </div>
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Referensi</label>
                                <input class="form-control bg-light" value="{{ $resep->referensi }}" readonly>
                            </div>
                        </div>
                        <div class="col-6">
                            <div class="mb-3">
                                <label for="defaultFormControlInput" class="form-label">Deskripsi</label>
                                <textarea class="form-control bg-light" cols="30" rows="12" readonly>{{ $resep->deskripsi }}</textarea>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6">
                            <form action="{{ route('bahan.store') }}" method="POST">
                                @csrf
                                <input type="hidden" name="resep_id" value="{{ $resep->id }}">
                                <div class="mb-3">
                                    <label for="defaultFormControlInput" class="form-label">Bahan</label>
                                    <input class="form-control" name="deskripsi" type="text">
                                </div>
                                <div class="mb-3">
                                    <button type="submit" class="btn btn-primary">Simpan</button>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-6">
                            <form action="{{ route('cara_membuat.store') }}" method="POST">
                                @csrf
                                <input type="hidden" name="resep_id" value="{{ $resep->id }}">
                                <div class="mb-3">
                                    <label for="defaultFormControlInput" class="form-label">Cara Buat</label>
                                    <input class="form-control" name="deskripsi" type="text">
                                </div>
                                <div class="mb-3">
                                    <button type="submit" class="btn btn-primary">Simpan</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="card my-5">
                            <div class="card-header">
                                <div class="text-start pt-3 pt-md-0">
                                    <span class="d-none d-sm-inline-block">Table Bahan</span>
                                </div>
                                <div class="card-datatable table-responsive mt-3">
                                    <table class="datatables-users table border-top" id="bahan">
                                        <thead>
                                            <tr>
                                                <th>No.</th>
                                                <th>Deskripsi</th>
                                                <th>Aksi</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            @foreach ($bahan as $item)
                                                <tr>
                                                    <td>{{ $loop->iteration }}</td>
                                                    <td>{{ Str::limit($item->deskripsi, 30) }}</td>
                                                    <td>
                                                        <button class="btn btn-danger btn-sm" type="button"
                                                            data-bs-toggle="modal"
                                                            data-bs-target="#delete-modal-{{ $item->id }}"><span><i
                                                                    class="bx bx-trash "></i>
                                                            </span>
                                                        </button>
                                                    </td>
                                                </tr>
                                                @include('admin.resep-makanan.delete-2')
                                            @endforeach
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="card my-5">
                            <div class="card-header">
                                <div class="text-start pt-3 pt-md-0">
                                    <span class="d-none d-sm-inline-block">Table Cara Membuat</span>
                                </div>
                                <div class="card-datatable table-responsive mt-3">
                                    <table class="datatables-users table border-top" id="cara_membuat">
                                        <thead>
                                            <tr>
                                                <th>No.</th>
                                                <th>Deskripsi</th>
                                                <th>Aksi</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            @foreach ($cara_membuat as $item)
                                                <tr>
                                                    <td>{{ $loop->iteration }}</td>
                                                    <td>{{ Str::limit($item->deskripsi, 30) }}</td>
                                                    <td>
                                                        <button class="btn btn-danger btn-sm" type="button"
                                                            data-bs-toggle="modal"
                                                            data-bs-target="#delete-modal-{{ $item->id }}"><span><i
                                                                    class="bx bx-trash "></i>
                                                            </span>
                                                        </button>
                                                    </td>
                                                </tr>
                                                @include('admin.resep-makanan.delete-2')
                                            @endforeach
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex justify-content-end">
                        <a href="{{ route('resep.index') }}" class="btn btn-danger">Kembali</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection

@push('script')
<script src="{{ asset('vendor/libs/datatables/jquery.dataTables.js') }}"></script>
<script src="{{ asset('vendor/libs/datatables-bs5/datatables-bootstrap5.js') }}"></script>
<script src="{{ asset('vendor/libs/datatables-responsive/datatables.responsive.js') }}"></script>
<script src="{{ asset('vendor/libs/datatables-responsive-bs5/responsive.bootstrap5.js') }}"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#bahan').DataTable({
            "pageLength": 5,
            scrollX: true,
        });
    });
</script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#cara_membuat').DataTable({
            "pageLength": 5,
            scrollX: true,
        });
    });
</script>
@endpush
