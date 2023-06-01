@extends('admin.base')

@push('style')
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-bs5/datatables.bootstrap5.css') }}" />
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-responsive-bs5/responsive.bootstrap5.css') }}" />
    <link rel="stylesheet" href="{{ asset('vendor/libs/datatables-buttons-bs5/buttons.bootstrap5.css') }}" />
@endpush


@section('title', 'Member')
@section('content')

    {{-- sweetalert --}}
    @include('sweetalert::alert')
    {{-- end sweetalert --}}
    <div class="container-xxl flex-grow-1 container-p-y">
        <h4 class="fw-bold py-3 mb-4">Member</h4>

        <div class="card">
            <div class="card-header flex-column flex-md-row">
                <div class="card-datatable table-responsive mt-3">
                    <table class="datatables-users table border-top" id="resep-makanan">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Nama</th>
                                <th>Email</th>
                                <th>Roles</th>
                                <th>Aksi</th>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach ($user as $item)
                                <tr>
                                    <td>{{ $loop->iteration }}</td>
                                    <td>{{ $item->nama }}</td>
                                    <td>{{ $item->email }}</td>
                                    <td>{{ $item->roles }}</td>
                                    <td>
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
        @include('admin.user.delete')
    </div>
@endsection

@push('script')
    <script src="{{ asset('vendor/libs/datatables/jquery.dataTables.js') }}"></script>
    <script src="{{ asset('vendor/libs/datatables-bs5/datatables-bootstrap5.js') }}"></script>
    <script src="{{ asset('vendor/libs/datatables-responsive/datatables.responsive.js') }}"></script>
    <script src="{{ asset('vendor/libs/datatables-responsive-bs5/responsive.bootstrap5.js') }}"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#resep-makanan').DataTable({
                "pageLength": 5,
                scrollX: true,
            });
        });
    </script>
@endpush
