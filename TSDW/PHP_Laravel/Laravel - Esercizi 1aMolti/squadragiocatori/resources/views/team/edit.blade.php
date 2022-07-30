@extends('layout')

@section('title')
    Edit
@endsection

@section('header')
    Modifica squadra:
@endsection

@section('content')
    <form action="/teams/{{$team->id}}" method="POST">
        @csrf
        @method('patch')
        <input type="text" name="nome" value="{{$team->nome}}">
        <input type="text" name="nazione" value="{{$team->nazione}}">
        <input type="date" name="d_fondazione" value="{{$team->d_fondazione}}">
        <input type="submit" value="Salva modifiche">
    </form>

    <hr>

    <a href="/teams/{{$team->id}}">Annulla</a>
@endsection