@extends('layout')

@section('title')
    Create
@endsection

@section('header')
    Crea squadra:
@endsection

@section('content')
    <form action="/teams" method="POST">
        @csrf
        <input type="text" name="nome" placeholder="nome">
        <input type="text" name="nazione" placeholder="nazione">
        <input type="date" name="d_fondazione">
        <input type="submit" value="Crea">
    </form>

    <hr>

    <a href="/">Annulla</a>
@endsection