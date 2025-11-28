import { useState } from 'react';
import { Pencil, Trash2, Plus } from 'lucide-react';
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
]

export default function Questions() {
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [currentQuestion, setCurrentQuestion] = useState(null);

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

  return (
    <div className={"questions"}>
      <button
        onClick={handleAddClick}
      >
        <Plus size={16} /> Добавить вопрос
      </button>

      <table>
        <thead>
        <tr>
          <th>Вопросы</th>
          <th>Ответы</th>
          <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        {questionsData.map((question, index) => (
          <tr key={index}>
            <td>{question.quest}</td>
            <td>{question.answer}</td>
            <td>
              <div>
                <div
                  onClick={() => handleEditClick(question)}
                >
                  <Pencil size={15} />
                </div>

                <div>
                  <Trash2 size={15} />
                </div>
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

            <div>
              <label>Вопрос:</label> <br/>
              <input
                type="text"
                defaultValue={currentQuestion?.quest || ""}
                placeholder="Введите текст вопроса"
              />
            </div>

            <div>
              <label>Ответ:</label> <br/>
              <input
                type="text"
                defaultValue={currentQuestion?.answer || ""}
                placeholder="Введите текст ответа"
              />
            </div>

            <div>
              <button onClick={handleCloseModal}>Отмена</button>
              <button onClick={handleCloseModal}>
                {currentQuestion ? 'Сохранить' : 'Создать'}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  )
}
