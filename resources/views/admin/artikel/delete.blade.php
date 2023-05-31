@foreach ($data as $artikel)
<div class="modal fade" id="delete-modal-{{ $artikel->id }}" tabindex="-1" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Hapus Artikel</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                </button>
            </div>
            <form action="{{ route('artikel.destroy', $artikel->id) }}" method="post">
                @method('DELETE')
                @csrf
                <input type="hidden" name="id" id="id" value="{{ $artikel->id }}">
                <div class="modal-body">
                    Anda yakin ingin menghapus data <b>{{ $artikel->judul }}</b>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">
                        <i class="bx bx-x d-block d-sm-none"></i>
                        <span class="d-none d-sm-block">Tutup</span>
                    </button>
                    <button type="submit" class="btn btn-outline-danger ml-1" id="btn-save">
                        <i class="bx bx-check d-block d-sm-none"></i>
                        <span class="d-none d-sm-block">Yakin</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
@endforeach

