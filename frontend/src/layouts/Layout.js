import React from 'react'
import Header from '../layouts/NavBar'
import Footer from '../layouts/Footer'

export default function Layout({ children }) {
  return (
    <>
      <Header />
      <div className="layout">
        {children}
      </div>
      <Footer />
    </>
  )
}
