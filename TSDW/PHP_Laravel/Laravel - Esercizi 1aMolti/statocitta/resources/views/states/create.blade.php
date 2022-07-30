@extends('layout')

@section('title')
    Create
@endsection

@section('header')
    Aggiungi stato
@endsection

@section('content')
    <form action="/states " method="POST">
        @csrf
        <input type="text" name="nome" placeholder="Nome">
        <input type="text" name="popolazione" placeholder="Popolazione">
        <input type="submit" value="Aggiungi stato">
    </form>

    <p><a href='/'>Annulla</a></p>
@endsection