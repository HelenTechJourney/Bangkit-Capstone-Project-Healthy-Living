<aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
    <div class="app-brand demo">
        <a href="#" class="app-brand-link">
            <span class="app-brand-logo demo">
                <img src="{{ asset('front/img/logo/logo.png ') }}" width="50" alt="">
            </span>
            <span class="app-brand-text demo menu-text fw-bolder ms-2">admin</span>
        </a>

        <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto">
            <i class="bx bx-chevron-left bx-sm align-middle"></i>
        </a>
    </div>

    <div class="menu-inner-shadow"></div>

    <ul class="menu-inner py-1 mb-5">
        <!-- Page -->
        <li class="menu-item">
            <a href="#" class="menu-link">
                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                <div>Dashboard</div>
            </a>
        </li>
        <li class="menu-header small text-uppercase">
            <span class="menu-header-text">Master Data</span>
        </li>
        <li class="menu-item {{ request()->is('admin/kirim-hewan*') ? 'active' : '' }}">
            <a href="#" class="menu-link">
                <i class='menu-icon bx bxs-data'></i>
                <div>Artikel</div>
            </a>
        </li>
        <li class="menu-item {{ request()->is('user/resep*') ? 'active' : '' }}">
            <a href="{{route('resep.index')}}" class="menu-link">
                <i class='menu-icon bx bxs-data'></i>
                <div>Resep Makanan</div>
            </a>
        </li>
        <li class="menu-header small text-uppercase">
            <span class="menu-header-text">User</span>
        </li>
        <li class="menu-item {{ request()->is('admin/user*') ? 'active' : '' }}">
            <a href="#" class="menu-link">
                <i class='menu-icon bx bxs-user-circle'></i>
                <div>Member</div>
            </a>
        </li>
    </ul>
</aside>
