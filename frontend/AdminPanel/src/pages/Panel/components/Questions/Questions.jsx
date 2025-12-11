import { useState, useEffect } from 'react';
import { Pencil, Trash2, Plus } from 'lucide-react';
import './Questions.css';

const API_URL = 'http://localhost:8080/v1/questions';

export default function Questions() {
  const [questions, setQuestions] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentQuestion, setCurrentQuestion] = useState(null);

  const [formData, setFormData] = useState({ question: '', answer: '' });

  useEffect(() => {
    const fetchQuestions = async () => {
      try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Ошибка при загрузке данных');

        const data = await response.json();
        console.log('GET ответ:', data);

        if (data.content) {
          setQuestions(data.content);
        } else if (Array.isArray(data)) {
          setQuestions(data);
        } else {
          setQuestions([]);
        }
      } catch (err) {
        console.error(err);
        setError('Не удалось загрузить вопросы');
      } finally {
        setIsLoading(false);
      }
    };

    fetchQuestions();
  }, []);

  const handleAddClick = () => {
    setCurrentQuestion(null);
    setFormData({ question: '', answer: '' });
    setIsModalOpen(true);
  };

  const handleEditClick = (question) => {
    setCurrentQuestion(question);
    setFormData({
      question: question.question,
      answer: question.answer
    });
    setIsModalOpen(true);
  };

  const handleCloseModal = () => {
    setIsModalOpen(false);
    setCurrentQuestion(null);
  };

  const handleSave = async () => {
    if (!formData.question.trim() || !formData.answer.trim()) {
      alert("Пожалуйста, заполните оба поля");
      return;
    }

    try {
      const method = currentQuestion ? 'PUT' : 'POST';
      const url = currentQuestion
        ? `${API_URL}/${currentQuestion.id}`
        : API_URL;

      const response = await fetch(url, {
        method: method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) {
        throw new Error('Ошибка при сохранении');
      }

      const savedItem = await response.json();
      console.log('Сохранено:', savedItem);

      if (currentQuestion) {
        setQuestions(prev => prev.map(q => q.id === savedItem.id ? savedItem : q));
      } else {
        setQuestions(prev => [...prev, savedItem]);
      }

      handleCloseModal();
    } catch (err) {
      console.error("Ошибка сохранения:", err);
      alert("Не удалось сохранить изменения");
    }
  };

  const handleDeleteClick = async (id) => {
    if (!window.confirm("Вы уверены, что хотите удалить этот вопрос?")) {
      return;
    }

    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        setQuestions(prev => prev.filter(q => q.id !== id));
      } else {
        alert("Ошибка при удалении");
      }
    } catch (err) {
      console.error("Ошибка удаления:", err);
    }
  };


  return (
    <div className="questions">
      <button className="btn-add" onClick={handleAddClick}>
        <Plus size={16} /> Добавить вопрос
      </button>

      {isLoading && <div className="status-message">Загрузка вопросов...</div>}
      {error && <div className="status-message error">{error}</div>}

      {!isLoading && !error && (
        <table className="questions-table">
          <thead>
          <tr>
            <th>Вопрос</th>
            <th>Ответ</th>
            <th>Действия</th>
          </tr>
          </thead>
          <tbody>
          {questions.length > 0 ? (
            questions.map((question, index) => (
              <tr key={question.id || index}>
                <td>{question.question}</td>
                <td>{question.answer}</td>
                <td>
                  <div className="actions">
                    <button onClick={() => handleEditClick(question)} title="Редактировать">
                      <Pencil size={16} />
                    </button>
                    <button onClick={() => handleDeleteClick(question.id)} title="Удалить" className="btn-delete-icon">
                      <Trash2 size={16} />
                    </button>
                  </div>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="3" style={{ textAlign: 'center' }}>
                Вопросы не найдены
              </td>
            </tr>
          )}
          </tbody>
        </table>
      )}

      {isModalOpen && (
        <div className="modal-overlay">
          <div className="modal-content">
            <h2>{currentQuestion ? 'Редактирование' : 'Новый вопрос'}</h2>

            <div className="form-group">
              <label>Вопрос:</label>
              <input
                type="text"
                value={formData.question}
                onChange={(e) => setFormData({...formData, question: e.target.value})}
                placeholder="Введите текст вопроса"
                className="form-input"
              />
            </div>

            <div className="form-group">
              <label>Ответ:</label>
              <textarea
                value={formData.answer}
                onChange={(e) => setFormData({...formData, answer: e.target.value})}
                placeholder="Введите текст ответа"
                className="form-textarea"
                rows="6"
              />
              <div className="formatting-toolbar">
                <button><strong>B</strong></button>
                <button><em>I</em></button>
                <button>•</button>
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
