import { Route, Routes } from 'react-router-dom'
import './App.css'
import Footer from './components/Footer'
import Header from './components/Header'
import Home from './pages/Home'
import Register from './pages/Register'
import Login from './pages/Login'
import Search from './pages/Search'
import User from './pages/User'
import Group from './pages/Group'
import Movie from './pages/Movie'

function App() {
  return (
    <>
      <Header></Header>
      <div className='container'>
        <Routes>
          <Route path='/home' element={<Home />} />
          <Route path='/register' element={<Register />} />
          <Route path='/login' element={<Login />} />
          <Route path='/search' element={<Search />} />
          <Route path='/user' element={<User />} />
          <Route path='/group' element={<Group />} />
          <Route path='/movie' element={<Movie />} />
        </Routes>
      </div>
      <Footer></Footer>
    </>
  );
}

export default App;
