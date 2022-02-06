import React from "react"

import { Link } from 'react-router-dom'
import TopMenu from "./../Menu/TopMenu";

function Header() {
    return (
        <header>
            <nav>
            <TopMenu />
            </nav>
        </header>
    )
}

export default Header