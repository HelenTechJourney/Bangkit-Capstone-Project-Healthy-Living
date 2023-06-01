@extends('admin.base')

@section('content')
@section('title', 'Dashboard')
@php
    use App\Models\Resep;
    use App\Models\artikel;
    use App\Models\User;

@endphp
<div class="container-xxl flex-grow-1 container-p-y">
    <div class="row">
        <div class="col">
            <div class="card">
                <div class="d-flex align-items-center row">
                    <div class="col-sm-7">
                        <div class="card-body">
                            <h5 class="card-title text-primary">Selamat Datang,</h5>
                            <h3 class="card-title text-primary">{{ Auth::user()->nama }}</h3>
                        </div>
                    </div>
                    <div class="col-sm-5 text-center text-sm-left">
                        <div class="card-body pb-0 px-0 px-md-4">
                            <img src="{{ asset('img/illustrations/man-with-laptop-light.png') }}" height="140"
                                alt="View Badge User" data-app-dark-img="illustrations/man-with-laptop-dark.png"
                                data-app-light-img="illustrations/man-with-laptop-light.png">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row mt-5">
        <div class="col-md-6 col-xl-4">
            <div class="card bg-success text-white mb-3">
                <div class="card-header">Jumlah Data User</div>
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <svg xmlns="http://www.w3.org/2000/svg" width="72" height="72" viewBox="0 0 24 24"
                            style="fill: #fff;transform: ;msFilter:;">
                            <path
                                d="M12 2A10.13 10.13 0 0 0 2 12a10 10 0 0 0 4 7.92V20h.1a9.7 9.7 0 0 0 11.8 0h.1v-.08A10 10 0 0 0 22 12 10.13 10.13 0 0 0 12 2zM8.07 18.93A3 3 0 0 1 11 16.57h2a3 3 0 0 1 2.93 2.36 7.75 7.75 0 0 1-7.86 0zm9.54-1.29A5 5 0 0 0 13 14.57h-2a5 5 0 0 0-4.61 3.07A8 8 0 0 1 4 12a8.1 8.1 0 0 1 8-8 8.1 8.1 0 0 1 8 8 8 8 0 0 1-2.39 5.64z">
                            </path>
                            <path
                                d="M12 6a3.91 3.91 0 0 0-4 4 3.91 3.91 0 0 0 4 4 3.91 3.91 0 0 0 4-4 3.91 3.91 0 0 0-4-4zm0 6a1.91 1.91 0 0 1-2-2 1.91 1.91 0 0 1 2-2 1.91 1.91 0 0 1 2 2 1.91 1.91 0 0 1-2 2z">
                            </path>
                        </svg>
                        <h3 class="text-white" style="font-size: 72px">{{ User::count() - 1 }}</h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xl-4">
            <div class="card bg-primary text-white mb-3">
                <div class="card-header">Jumlah Data Artikel</div>
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <svg xmlns="http://www.w3.org/2000/svg" width="72" height="72" viewBox="0 0 24 24"
                            style="fill: #fff;transform: ;msFilter:;">
                            <path
                                d="M20 6c0-2.168-3.663-4-8-4S4 3.832 4 6v2c0 2.168 3.663 4 8 4s8-1.832 8-4V6zm-8 13c-4.337 0-8-1.832-8-4v3c0 2.168 3.663 4 8 4s8-1.832 8-4v-3c0 2.168-3.663 4-8 4z">
                            </path>
                            <path d="M20 10c0 2.168-3.663 4-8 4s-8-1.832-8-4v3c0 2.168 3.663 4 8 4s8-1.832 8-4v-3z">
                            </path>
                        </svg>
                        <h3 class="text-white" style="font-size: 72px">{{ artikel::count() }}</h3>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-6 col-xl-4">
            <div class="card bg-secondary text-white mb-3">
                <div class="card-header">Jumlah Data Resep Makanan</div>
                <div class="card-body">
                    <div class="d-flex justify-content-between">
                        <svg xmlns="http://www.w3.org/2000/svg" width="72" height="72" viewBox="0 0 24 24"
                            style="fill: #fff;transform: ;msFilter:;">
                            <path
                                d="M20 6c0-2.168-3.663-4-8-4S4 3.832 4 6v2c0 2.168 3.663 4 8 4s8-1.832 8-4V6zm-8 13c-4.337 0-8-1.832-8-4v3c0 2.168 3.663 4 8 4s8-1.832 8-4v-3c0 2.168-3.663 4-8 4z">
                            </path>
                            <path d="M20 10c0 2.168-3.663 4-8 4s-8-1.832-8-4v3c0 2.168 3.663 4 8 4s8-1.832 8-4v-3z">
                            </path>
                        </svg>
                        <h3 class="text-white" style="font-size: 72px">{{ Resep::count() }}</h3>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
