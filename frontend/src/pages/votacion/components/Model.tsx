import { AiFillCloseCircle } from "react-icons/ai";
export const Modal: React.FC<{
  children: React.ReactNode;
  isOpen: boolean;
  closeModal: () => void;
  widthModal: number;
  heightModal: number;
}> = ({ children, isOpen, closeModal,heightModal,widthModal }) => {
  const handleClick = (event: React.MouseEvent) => {
    event.stopPropagation();
  };
  const handleClickButton = (event: React.MouseEvent) => {
    event.preventDefault();
    closeModal();
  }
  return (
    <div
      className={`flex fixed z-[999] top-0 left-0 w-full h-full min-h-full bg-opacity-75 bg-black justify-center items-center text-black ${
        isOpen ? " fixed" : "hidden "
      }`}
      onClick={closeModal}
    >
      <div className={`relative bg-white p-3 h-[${heightModal}em] w-[${widthModal}em] overflow-auto`} onClick={handleClick}>
        <button
          className="absolute text-sm text-white top-1 right-2"
          onClick={handleClickButton}
        >
          <AiFillCloseCircle
            size={20}
            className="text-black hover:text-gray-700"
          />
        </button>
        {children}
      </div>
    </div>
  );
};

export default Modal;