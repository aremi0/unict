@extends('layout')

@section('title')
    Edit
@endsection

@section('header')
    Modifica giocatore:
@endsection

@section('content')
    <form action="/players/{{$player->id}}" method="POST">
        @csrf
        @method('patch')
        <input type="hidden" name="id_team" value="{{$player->id_team}}">
        <input type="text" name="nome" value="{{$player->nome}}">
        <input type="number" name="eta" value="{{$player->eta}}">
        <input type="submit" value="Modifica giocatore">
    </form>

    <hr>

    <a href="/players/{{$player->id}}">Annulla</a>
@endsection