@extends('layout')

@section('title')
    Create
@endsection

@section('header')
    Crea città
@endsection

@section('content')
    <form action="/cities" method="POST">
        @csrf
        <input type="text" name="nome" placeholder="Nome">
        <input type="text" name="regione" placeholder="Regione">
        <input type="hidden" name="id_states" value={{$id}}>
        <input type="submit" value="Aggiungi città">
    </form>

    <p><a href='/'>Annulla</a></p>
@endsection