@extends('layout')

@section('title')
    Create
@endsection

@section('header')
    Inserisci nuovo libro:
@endsection

@section('content')
    <form method="POST" action="/book">
        @csrf
        <input type="hidden" name="id_autore" value="{{$id}}">
        <input type="text" name="nome" placeholder="Inserisci il nome">
        <input type="number" name="pagine">
        <input type="submit">
    </form>

    <a href='/author/{{$id}}'>Annulla</a>
@endsection