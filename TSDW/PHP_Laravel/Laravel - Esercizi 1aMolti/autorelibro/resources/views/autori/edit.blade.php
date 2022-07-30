@extends('layout')

@section('title')
    Edit
@endsection

@section('header')
    Modifica {{$author->nome}}:
@endsection

@section('content')
    <form method="POST" action="/author/{{$author->id}}">
        @csrf
        @method('patch')
        <input type="text" name="nome" placeholder="{{$author->nome}}">
        <input type="date" name="nascita" value="{{$author->nascita}}">
        <input type="submit">
    </form>

    <a href='/author/{{$author->id}}'>Annulla</a>
@endsection