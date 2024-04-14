import React from 'react';
import {Routes, Route } from 'react-router-dom';
import './App.css';
import Layout from './layouts/Layout';
import Home from './pages/Home';
import SignUp from './pages/SignUp';
import Login from './pages/Login';
import Search from './pages/Search';
import User from './pages/User';
import UserDetail from './pages/UserDetail';
import Group from './pages/Group';
import GroupDetail from './pages/GroupDetail';
import Movie from './pages/Movie';
import MovieDetail from './pages/MovieDetail';
import Showtimes from './pages/Showtimes';
import NotFound from './pages/NotFound';


function App() {
  return (
    <Layout>
      <Routes>
        <Route path='/' element={<Home />} /> {/*or Index?*/}
        <Route path='/signup' element={<SignUp />} />
        <Route path='/login' element={<Login />} />
        <Route path='/search' element={<Search />} />
        <Route path='/user' element={<User />} />
        <Route path='/group' element={<Group />} />
        <Route path='/group/:id' element={<GroupDetail />} />
        <Route path='/users/:id' element={<UserDetail />} />
        <Route path='/movie/:id' element={<MovieDetail />} />
        <Route path='/movie' element={<Movie />} />
        <Route path='/showtimes' element={<Showtimes />} />
        <Route path='*' element={<NotFound />} />
      </Routes>
    </Layout>
  );
}

export default App;
