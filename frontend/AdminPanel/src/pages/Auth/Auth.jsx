import {NavLink} from "react-router-dom";

export default function Auth() {
  return(
    <div className={"auth"}>
      <div className={"login"}>
        <span>Логин</span>
        <input type={"text"} placeholder={"Введите логин..."}/>
      </div>
      <div className={"password"}>
        <span>Пароль</span>
        <input type={"password"} placeholder={"Введите пароль..."}/>
      </div>
      <div className={"auth-button"}>
        <NavLink to={'/panel'}>
          <button>Войти</button>
        </NavLink>
      </div>
    </div>
  )
}
