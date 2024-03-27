import { Route, Routes } from 'react-router-dom';
import './App.css';
import Footer from './components/Footer';
import Header from './components/Header';
import Home from './pages/Home';

function App() {
  return (
    <>
      <Header></Header>
      <div className='container'>
        <Routes>
          <Route path='/home' element={<Home />} />
        </Routes>
      </div>
      <Footer></Footer>
    </>
  );
}

export default App;
