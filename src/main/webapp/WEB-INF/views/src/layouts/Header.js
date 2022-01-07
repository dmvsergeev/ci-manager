import React from "react"

import { Link } from 'react-router-dom'

function Header() {
    return (
        <header>
            <nav>
                <ul className="topnav">
                    <li><Link className="btn btn-default btn-sm" to='/'>Главная</Link></li>
                    <li><Link className="btn btn-default btn-sm" to='/allusers'>Пользователи</Link></li>
                    <li><Link className="btn btn-default btn-sm" to='/createuser'>Создать пользователя</Link></li>
                    <li><Link className="btn btn-default btn-sm" to='/apps'>Приложения</Link></li>
                    <li><Link className="btn btn-default btn-sm" to='/createapp'>Создать Приложение</Link></li>
                    <li><Link className="btn btn-default btn-sm" to='/passwords'>Мои Пароли</Link></li>
                </ul>
            </nav>
        </header>
    )
}

export default Header