@extends('admin.base')

@push('style')
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-bs5/datatables.bootstrap5.css') }}" />
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-responsive-bs5/responsive.bootstrap5.css') }}" />
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-buttons-bs5/buttons.bootstrap5.css') }}" />
@endpush
@include('sweetalert.alert')
@section('title', 'Artikel')
@section('content')
    <div class="container-xxl flex-grow-1 container-p-y">
        <h4 class="fw-bold py-3 mb-4">Artikel</h4>

        <div class="card">
            <div class="card-header flex-column flex-md-row">
                <div class="text-end pt-3 pt-md-0">
                    <a href="{{ route('artikel.create') }}" class="btn btn-primary">
                        <span><i class="bx bx-plus me-sm-2"></i><span class="d-none d-sm-inline-block">Tambah
                                Artikel</span></span>
                    </a>
                </div>
                <div class="card-datatable table-responsive mt-3">
                    <table class="datatables-users table border-top" id="artikel">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Judul</th>
                                <th>Deskripsi</th>
                                <th>Aksi</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach ($data as $item)
                                <tr>
                                    <td>{{ $loop->iteration }}</td>
                                    <td>{{ $item->judul }}</td>
                                    <td>{!! Str::limit($item->deskripsi, 30) !!}</td>
                                    <td>
                                        <a href="{{route('artikel.edit',$item->id)}}" class="btn btn-success btn-sm">
                                            <i class="bx bx-edit"></i>
                                        </a>
                                        <a href="{{route('artikel.show',$item->id)}}" class="btn btn-primary btn-sm">
                                            <i class='bx bxs-show'></i>
                                        </a>

                                        <button class="btn btn-danger btn-sm" type="button" data-bs-toggle="modal"
                                            data-bs-target="#delete-modal-{{ $item->id }}"><span><i
                                                    class="bx bx-trash "></i>
                                                   </span>
                                        </button>
                                    </td>
                                </tr>
                            @endforeach
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        @include('admin.artikel.delete')
    </div>
@endsection

@push('script')
    <script src="{{ asset('vendor/libs/datatables/jquery.dataTables.js') }}"></script>
    <script src="{{ asset('vendor/libs/datatables-bs5/datatables-bootstrap5.js') }}"></script>
    <script src="{{ asset('vendor/libs/datatables-responsive/datatables.responsive.js') }}"></script>
    <script src="{{ asset('vendor/libs/datatables-responsive-bs5/responsive.bootstrap5.js') }}"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#artikel').DataTable({
                "pageLength": 5,
                scrollX: true,
            });
        });
    </script>
@endpush
