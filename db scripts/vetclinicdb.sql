--
-- Zrzut danych tabeli `customer`
--

INSERT INTO `customer` (`id`, `first_name`, `last_name`, `pin`) VALUES
(1, 'Jan', 'Kowalski', 1212),
(2, 'Piotr', 'Nowak', 202),
(3, 'Krzysztof', 'Krawczyk', 303);


--
-- Zrzut danych tabeli `doctor`
--

INSERT INTO `doctor` (`id`, `first_name`, `last_name`) VALUES
(1, 'John', 'Dollittle'),
(2, 'John', 'Doe');

--
-- Zrzut danych tabeli `appointment`
--

INSERT INTO `appointment` (`id`, `appointment_date_and_time`, `customer_id`, `doctor_id`) VALUES
(1, '2021-01-03 02:35:58', 1, 1),
(2, '2021-01-03 03:35:00', 2, 1);


-- --------------------------------------------------------

