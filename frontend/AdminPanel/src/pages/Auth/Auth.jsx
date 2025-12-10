import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import eiosIcon from '../../assets/EIOS.png';
import './auth.css';

export default function Auth() {
  const navigate = useNavigate();

  const [login, setLogin] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    if (login.trim() && password.trim()) {
      setError('');
      navigate('/panel');
    } else {
      setError('Неверный логин или пароль');
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card">

        <div className="auth-header">
          <img src={eiosIcon} alt="Логотип" className="auth-icon" />
          <h1>Панель администратора</h1>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="login">Логин</label>
            <input
              id="login"
              type="text"
              className={`form-input ${error ? 'input-error' : ''}`}
              placeholder="Введите логин"
              value={login}
              onChange={(e) => setLogin(e.target.value)}
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Пароль</label>
            <input
              id="password"
              type="password"
              className={`form-input ${error ? 'input-error' : ''}`}
              placeholder="Введите пароль"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
          </div>

          {error && <div className="error-message">{error}</div>}

          <div className="auth-actions">
            <button type="submit" className="btn-login">
              Войти
            </button>
          </div>
        </form>

      </div>
    </div>
  );
}
