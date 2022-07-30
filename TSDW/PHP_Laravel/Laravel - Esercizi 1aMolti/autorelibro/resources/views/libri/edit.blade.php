@extends('layout')

@section('title')
    Edit
@endsection

@section('header')
    Modifica {{$book->nome}}:
@endsection

@section('content')
    <form method="POST" action="/book/{{$book->id}}">
        @csrf
        @method('patch')
        <input type="text" name="nome" placeholder="{{$book->nome}}">
        <input type="number" name="pagine" value="{{$book->pagine}}">
        <input type="submit">
    </form>

    <a href='/book/{{$book->id}}'>Annulla</a>
@endsection