import "../../css/header.css";
import {useLocation} from "react-router-dom";
import {logout, selectUsername} from "../auth/authSlice";
import {apiSlice} from "../../app/apiSlice";
import {useDispatch, useSelector} from "react-redux";
import {useEffect} from "react";

const Header = ({
                    studentName = 'Gurskaya Yulia',
                    studentGroup = 'P3220',
                    variant = '2023'
                }) => {
    const location = useLocation();
    const dispatch = useDispatch()
    const isMainPage = location.pathname === '/';
    const username = useSelector(selectUsername);

    const logoutHandler = (event) => {
        dispatch(logout())
        dispatch(apiSlice.util.resetApiState())
    }

    useEffect(() => {
        const handleStorageChange = () => {
            const numericKeys = Object.keys(localStorage).filter(key => /^\d+$/.test(key));
            if (!(numericKeys.length > 0)) {
                dispatch(logout());
            }
        };

        window.addEventListener("storage", handleStorageChange);

        return () => {
            window.removeEventListener("storage", handleStorageChange);
        };
    }, [dispatch]);


    const menuIconClickCallback = (iconMenu) => {
        if (iconMenu) {
            iconMenu.classList.toggle('_active');
            const headerTitle = document.querySelector('.header__title');
            const menuBody = document.querySelector('.menu__body');
            if (headerTitle && menuBody) {
                headerTitle.classList.toggle('_active');
                menuBody.classList.toggle('_active');
                document.body.classList.toggle('_lock');
            }
        }
    };

    return <div className="header">
        <div className="header__row">
            <div className="header__title">
                <a href="https://www.youtube.com/watch?v=g7tzDP3oI2Y">{studentName} | {studentGroup} | #{variant} <img src="https://gas-kvas.com/uploads/posts/2023-01/1673339552_gas-kvas-com-p-lapki-risunki-anime-1.jpg" id="header_img" /></a>
            </div>
            {isMainPage ? (
                <div className="header__menu menu">

                    <div className="menu__icon" onClick={(event) => menuIconClickCallback(event.currentTarget)}>
                        <span/>
                    </div>
                    <div className="menu__body">
                        <ul className="menu__list">
                            {username && (
                                <li className="menu__item">User: {username}</li>
                            )}                            <li className="menu__item">
                            <a href="/~s367177" onClick={event => logoutHandler(event)}>Logout</a>
                        </li>
                        </ul>
                    </div>
                </div>
            ) : (
                <span></span>
            )}
        </div>
    </div>
}



export default Header