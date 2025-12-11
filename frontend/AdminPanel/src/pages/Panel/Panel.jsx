import Questions from "./components/Questions/Questions.jsx";
import eiosIcon from '../../assets/EIOS.png';
import './Panel.css';

export default function Panel() {
  return (
    <div className="panel">
      <header className="panel-header">
        <div className="header-left">
          <img src={eiosIcon} alt="Логотип ЭИОС" className="eios-icon" />
        </div>

        <div className="header-right">
          <button className="btn-exit">
            <span className="btn-exit-icon">←</span>
            <span className="btn-exit-text">Выход</span>
          </button>
        </div>
      </header>

      <main className="panel-body">
        <div className="container">
          <h1 className="page-title">Панель администратора</h1>
          <Questions />
        </div>
      </main>
    </div>
  );
}
