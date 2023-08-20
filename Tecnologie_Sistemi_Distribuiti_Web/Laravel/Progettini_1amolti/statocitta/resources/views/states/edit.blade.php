@extends('layout')

@section('title')
    Edit
@endsection

@section('header')
    Modifica stato:
@endsection

@section('content')
    <form action="/states/{{$state->id}}" method="POST">
        @csrf
        @method('PATCH')
        <input type="text" name="nome" placeholder="Nuovo nome">
        <input type="text" name="popolazione" placeholder="Nuova popolazione">
        <input type="submit" value="Modifica stato">
    </form>

    <p><a href='/'>Annulla</a></p>
@endsection