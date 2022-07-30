@extends('layout')

@section('title')
    Nuovo autori
@endsection

@section('header')
    Inserisci nuovo autore:
@endsection

@section('content')
    <form method="POST" action="/author">
        @csrf
        <input type="text" name="nome" placeholder="Inserisci il nome">
        <input type="date" name="nascita">
        <input type="submit">
    </form>

    <a href='/'>Annulla</a>
@endsection