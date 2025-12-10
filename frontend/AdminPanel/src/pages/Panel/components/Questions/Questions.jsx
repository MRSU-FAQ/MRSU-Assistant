import { useState } from 'react';
import { Pencil, Trash2, Plus, Search } from 'lucide-react';
import './Questions.css';

const questionsData = [
  {
    quest: "Когда экзамены",
    answer: "Через 2 недели"
  },
  {
    quest: "Когда зачёты",
    answer: "Завтра"
  }
];

export default function Questions() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentQuestion, setCurrentQuestion] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');

  const filteredQuestions = questionsData.filter(q =>
    q.quest.toLowerCase().includes(searchTerm.toLowerCase()) ||
    q.answer.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleAddClick = () => {
    setCurrentQuestion(null);
    setIsModalOpen(true);
  };

  const handleEditClick = (question) => {
    setCurrentQuestion(question);
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setCurrentQuestion(null);
  };

  const handleSave = () => {
    console.log('Сохранено:', currentQuestion);
    handleCloseModal();
  };

  return (
    <div className="questions">
      <div className="search-box">
        <Search size={16} />
        <input
          type="text"
          placeholder="Поиск по вопросу или ответу..."
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
      </div>

      <button className="btn-add" onClick={handleAddClick}>
        <Plus size={16} /> Добавить вопрос
      </button>

      <table className="questions-table">
        <thead>
        <tr>
          <th>Вопрос</th>
          <th>Ответ</th>
          <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        {filteredQuestions.map((question, index) => (
          <tr key={index}>
            <td>{question.quest}</td>
            <td>{question.answer}</td>
            <td>
              <div className="actions">
                <button onClick={() => handleEditClick(question)} title="Редактировать">
                  <Pencil size={16} />
                </button>
                <button title="Удалить">
                  <Trash2 size={16} />
                </button>
              </div>
            </td>
          </tr>
        ))}
        </tbody>
      </table>

      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>{currentQuestion ? 'Редактирование' : 'Новый вопрос'}</h2>

            <div className="form-group">
              <label>Вопрос:</label>
              <input
                type="text"
                defaultValue={currentQuestion?.quest || ""}
                placeholder="Введите текст вопроса"
                className="form-input"
              />
            </div>

            <div className="form-group">
              <label>Ответ:</label>
              <textarea
                defaultValue={currentQuestion?.answer || ""}
                placeholder="Введите текст ответа (можно использовать жирный, курсив, списки)"
                className="form-textarea"
                rows="6"
              />
              <div className="formatting-toolbar">
                <button><strong>B</strong></button>
                <button><em>I</em></button>
                <button>•</button>
                <button>→</button>
                <button>🔗</button>
              </div>
            </div>

            <div className="modal-actions">
              <button className="btn-cancel" onClick={handleCloseModal}>Отмена</button>
              <button className="btn-save" onClick={handleSave}>
                {currentQuestion ? 'Сохранить' : 'Создать'}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
