import React from "react"

import { Link } from 'react-router-dom'

function Header() {
    return (
        <header>
            <nav>
                <ul class="topnav">
                    <li class="btn btn-default"><Link to='/'>Главная</Link></li>
                    <li class="btn btn-default"><Link to='/allusers'>Пользователи</Link></li>
                    <li class="btn btn-default"><Link to='/createuser'>Создать пользователя</Link></li>
                    <li class="btn btn-default"><Link to='/apps'>Приложения</Link></li>
                    <li class="btn btn-default"><Link to='/passwords'>Пароли</Link></li>
                </ul>
            </nav>
        </header>
    )
}

export default Header