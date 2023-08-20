@extends('layout')

@section('title')
    Create
@endsection

@section('header')
    Crea giocatore:
@endsection

@section('content')
    <form action="/players" method="POST">
        @csrf
        <input type="hidden" name="id_team" value="{{$id_team}}">
        <input type="text" name="nome" placeholder="nome">
        <input type="number" name="eta">
        <input type="submit" value="Crea">
    </form>

    <hr>

    <a href="/teams/{{$id_team}}">Annulla</a>
@endsection